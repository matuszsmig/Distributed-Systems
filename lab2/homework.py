import requests
import httpx
from fastapi import FastAPI, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from fastapi.responses import JSONResponse
from helpers import check_is_data_null, calculate_best_rating, calculate_percent
from dotenv import load_dotenv
import os

app = FastAPI()

load_dotenv()

COMMON_API_KEY = os.getenv("COMMON_API_KEY")
API_HOST_1 = "heisenbug-premier-league-live-scores-v1.p.rapidapi.com"
API_HOST_2 = "api-football-v1.p.rapidapi.com"

app.add_middleware(
    CORSMiddleware,
    allow_origins=["http://localhost:3000"],
    allow_credentials=True,
    allow_methods=["GET"]
)

@app.get("/teams/", response_class=JSONResponse)
async def get_all_teams():
    url = "https://heisenbug-premier-league-live-scores-v1.p.rapidapi.com/api/premierleague/table"

    querystring = {"season":"2023-24"}

    headers = {
        "X-RapidAPI-Key": COMMON_API_KEY,
        "X-RapidAPI-Host": API_HOST_1
    }

    try:
        response = requests.get(url, headers=headers, params=querystring)
        response.raise_for_status()

        response_teams = response.json()
        teams = []

        for object in response_teams["records"]:
            teams.append(object["team"])

        return teams

    except httpx.HTTPError as http_err:
        status_code = http_err.response.status_code if http_err.response else 500
        raise HTTPException(status_code=status_code, detail="Błąd komunikacji z serwisem zewnętrznym")

    except Exception as err:
        raise HTTPException(status_code=500, detail="Wystąpił nieoczekiwany błąd") from err

@app.get("/teams/{team}", response_class=JSONResponse)
async def get_team(team: str):
    all_teams = await get_all_teams()
    if team not in all_teams:
        raise HTTPException(status_code=404, detail="Podany zespół nie istnieje lub go nie obsługujemy")

    url = "https://api-football-v1.p.rapidapi.com/v3/teams"

    querystring = {"name": {team}}

    headers = {
        "X-RapidAPI-Key": COMMON_API_KEY,
        "X-RapidAPI-Host": API_HOST_2
    }

    
    response = requests.get(url, headers=headers, params=querystring)

    resposne_data = []
    filtred_inforamtions = {}

    if response.status_code == 200:
        resposne_data = response.json()
    else:
        raise HTTPException(status_code=404, detail="Nie udało się odnaleźć drużyny")
    
    team_data = resposne_data["response"][0]["team"]
    venue_data = resposne_data["response"][0]["venue"]

    filtred_inforamtions = {
        "id": team_data["id"],
        "name": team_data["name"],
        "founded": team_data["founded"],
        "logo": team_data["logo"],
        "stadium_name": venue_data["name"],
        "city": venue_data["city"],
        "capacity": venue_data["capacity"]
        }
    
    return filtred_inforamtions

@app.get("/teams/{team}/players", response_class=JSONResponse)
async def get_all_players_by_team(team: int):
    url = "https://api-football-v1.p.rapidapi.com/v3/players"

    querystring = {"team": {team},"season":"2023"}

    headers = {
        "X-RapidAPI-Key": COMMON_API_KEY,
        "X-RapidAPI-Host": API_HOST_2
    }

    response = requests.get(url, headers=headers, params=querystring)

    response_data = []
    players = []

    if response.status_code == 200:
        response_data = response.json()
    else:
        raise HTTPException(status_code=response.status_code, detail="Nie udało się odnalźć zawodników")

    for player in response_data["response"]:
        player_info = {
            "id": player["player"]["id"],
            "firstname": player["player"]["firstname"],
            "lastname": player["player"]["lastname"],
            "age": player["player"]["age"],
            "nationality": player["player"]["nationality"],
            "height": player["player"]["height"],
            "weight": player["player"]["weight"],
            "photo": player["player"]["photo"],
            "position": player["statistics"][0]["games"]["position"]
        }
        players.append(player_info)
        
    return players

@app.get("/teams/{team}/players/{player}", response_class=JSONResponse)
async def get_stats_by_player(team: int, player: int):
    url = "https://api-football-v1.p.rapidapi.com/v3/players"

    querystring = {"id": {player},"team": {team},"season":"2023"}

    headers = {
        "X-RapidAPI-Key": COMMON_API_KEY,
        "X-RapidAPI-Host": API_HOST_2
    }

    response = requests.get(url, headers=headers, params=querystring)

    response_data = []
    player_stats = {
        "appearences": 0,
        "minutes": 0,
        "rating": 0,
        "best_rating": 0,
        "number_of_rating": 0,
        "shots": 0,
        "goals": 0,
        "assists": 0,
        "passes": 0,
        "accuracy": 0,
        "accuracy_passes_percent": 0,
        "dribbles_attempts": 0,
        "dribbles_success": 0,
        "dribbles_success_percent": 0,
        "yellow_cards": 0,
        "red_cards": 0     
    }

    if response.status_code == 200:
        response_data = response.json()
    else:
        raise HTTPException(status_code=response.status_code, detail="Nie udało się odnalźć zawodnika")
    
    for data in response_data["response"][0]["statistics"]:
        player_stats["appearences"] += check_is_data_null(data["games"]["appearences"])
        player_stats["minutes"] += check_is_data_null(data["games"]["minutes"])
        player_stats["rating"] += float(check_is_data_null(data["games"]["rating"]))
        player_stats["best_rating"] = calculate_best_rating(data["games"]["rating"], player_stats["best_rating"])
        player_stats["number_of_rating"] += 1 if check_is_data_null(data["games"]["rating"]) != 0 else 0
        player_stats["shots"] += check_is_data_null(data["shots"]["total"])
        player_stats["goals"] += check_is_data_null(data["goals"]["total"])
        player_stats["assists"] += check_is_data_null(data["goals"]["assists"])
        player_stats["passes"] += check_is_data_null(data["passes"]["total"])
        player_stats["accuracy"] += check_is_data_null(data["passes"]["accuracy"])
        player_stats["dribbles_attempts"] += check_is_data_null(data["dribbles"]["attempts"])
        player_stats["dribbles_success"] += check_is_data_null(data["dribbles"]["success"])
        player_stats["yellow_cards"] += check_is_data_null(data["cards"]["yellow"])
        player_stats["red_cards"] += check_is_data_null(data["cards"]["red"])

    if (player_stats["number_of_rating"] != 0): player_stats["rating"] /= player_stats["number_of_rating"]
    player_stats["accuracy_passes_percent"] = calculate_percent(player_stats["passes"], player_stats["accuracy"])
    player_stats["dribbles_success_percent"] = calculate_percent(player_stats["dribbles_attempts"], player_stats["dribbles_success"])

    return player_stats

@app.get("/publicapi/teams/", response_class=JSONResponse)
async def get_all_teams_api():
    url = "https://heisenbug-premier-league-live-scores-v1.p.rapidapi.com/api/premierleague/table"

    querystring = {"season":"2023-24"}

    headers = {
        "X-RapidAPI-Key": COMMON_API_KEY,
        "X-RapidAPI-Host": API_HOST_1
    }

    response = requests.get(url, headers=headers, params=querystring)

    if response.status_code == 200:
        return response.json()
    else:
        raise HTTPException(status_code=response.status_code, detail="Nie udało się pobrać drużyn z premier league API")
    
@app.get("/publicapi/teams/{team}", response_class=JSONResponse)
async def get_team_api(team: str):
    url = "https://api-football-v1.p.rapidapi.com/v3/teams"

    querystring = {"name": {team}}

    headers = {
        "X-RapidAPI-Key": COMMON_API_KEY,
        "X-RapidAPI-Host": API_HOST_2
    }

    response = requests.get(url, headers=headers, params=querystring)

    if response.status_code == 200:
        return response.json()
    else:
        raise HTTPException(status_code=response.status_code, detail="Nie udało się odnalźć drużyny")
    
@app.get("/publicapi/teams/{team}/players", response_class=JSONResponse)
async def get_all_players_by_team_api(team: int):
    url = "https://api-football-v1.p.rapidapi.com/v3/players"

    querystring = {"team": {team},"season":"2023"}

    headers = {
        "X-RapidAPI-Key": COMMON_API_KEY,
        "X-RapidAPI-Host": API_HOST_2
    }

    response = requests.get(url, headers=headers, params=querystring)

    if response.status_code == 200:
        return response.json()
    else:
        raise HTTPException(status_code=response.status_code, detail="Nie udało się odnalźć zawodników")

@app.get("/publicapi/teams/{team}/players/{player}", response_class=JSONResponse)
async def get_stats_by_player_api(team: int, player: int):
    url = "https://api-football-v1.p.rapidapi.com/v3/players"

    querystring = {"id": {player},"team": {team},"season":"2023"}

    headers = {
        "X-RapidAPI-Key": COMMON_API_KEY,
        "X-RapidAPI-Host": API_HOST_2
    }

    response = requests.get(url, headers=headers, params=querystring)

    if response.status_code == 200:
        return response.json()
    else:
        raise HTTPException(status_code=response.status_code, detail="Nie udało się odnalźć zawodnika")