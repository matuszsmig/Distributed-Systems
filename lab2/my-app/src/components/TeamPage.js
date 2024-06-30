import React, { useCallback, useState, useEffect } from 'react';
import "./MainPage.css"
import PlayerStats from './PlayerStats';

const TeamPage = ({ teamData, isTeamData }) => {
    const [isPlayersData, setIsPlayersData] = useState(false);
    const [isPlayerData, setIsPlayerData] = useState(false);
    const [playersData, setPlayersData] = useState([]);
    const [playerData, setPlayerData] = useState({});

    useEffect(() => {
        if (!isTeamData || !teamData.id) {
            setIsPlayersData(false);
            setIsPlayerData(false);
        }
    }, [isTeamData, teamData.id]);

    const handleTeamButtonClick = useCallback(async () => {
        try {
            const response = await fetch(`http://localhost:8000/teams/${teamData.id}/players`);
            if (!response.ok) {
                setPlayersData(false);
                setIsPlayerData(false);
                throw new Error('Wystąpił błąd przy pobieraniu danych zespołu');
            }
            const data = await response.json();
            setPlayersData(data);
            setIsPlayersData(true);
        } catch (error) {
            console.error(error);
        }
    }, [teamData.id]);

    const handlePlayerClick = useCallback(async (playerId) => {
        try {
            const response = await fetch(`http://localhost:8000/teams/${teamData.id}/players/${playerId}`);
            if (!response.ok) {
                setIsPlayerData(false);
                throw new Error('Wystąpił błąd przy pobieraniu danych zespołu');
            }
            const data = await response.json();
            setPlayerData(data);
            setIsPlayerData(true);
        } catch (error) {
            console.error(error);
        }
    }, [teamData.id]);

    const playerInfo = useCallback((player) => {
        const handleClick = () => {
            handlePlayerClick(player.id);
        };

        return (
            <div className='player-container' onClick={handleClick}>
              <img src={player.photo} />
              <p>Imię: <b>{player.firstname}</b></p>
              <p>Nazwisko: <b>{player.lastname}</b></p>
              <p>Wiek: <b>{player.age}</b></p>
              <p>Narodowość: <b>{player.nationality}</b></p>
              <p>Pozycja: <b>{player.position}</b></p>
              <p>Wzrost: <b>{player.height}</b></p>
              <p>Waga: <b>{player.weight}</b></p>
            </div>
          );
    }, [handlePlayerClick]);

    return (
        <>
            <div>
                {teamData.id && (
                    <>
                        <p>Pełna nazwa klubu: <b>{teamData.name}</b></p>
                        <img src={teamData.logo} />
                        <p>Miasto z którego klub pochodzi: <b>{teamData.city}</b></p>
                        <p>Rok założenia: <b>{teamData.founded}</b></p>
                        <p>Nazwa stadionu: <b>{teamData.stadium_name}</b></p>
                        <p>Pojemność stadionu: <b>{teamData.capacity}</b></p>
                    </>
                )}
            </div>
            <button type="submit" onClick={handleTeamButtonClick}>Pobierz zawodników</button>
            <br />
            <div className='players'>
                {isPlayersData && playersData.map((player) => playerInfo(player))}
            </div>
            <br />
            {isPlayerData && <PlayerStats playerData={playerData}/>}
        </>
    );
};

export default TeamPage;
