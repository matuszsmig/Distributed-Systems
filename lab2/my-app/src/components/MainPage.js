import React, { useCallback, useState } from 'react';
import "./MainPage.css"
import TeamPage from './TeamPage';

const MainPage = () => {
    const [team, setTeam] = useState("");
    const [teamData, setTeamData] = useState([]);
    const [isTeamData, setIsTeamData] = useState(false);

    const handleChangeTeam = useCallback((event) => {
        setTeam(event.target.value);
    }, []);

    const handleButtonClick = useCallback(async () => {
        try {
            const response = await fetch(`http://localhost:8000/teams/${team}`);
            if (!response.ok) {
                setIsTeamData(false);
                throw new Error('Wystąpił błąd przy pobieraniu danych zespołu');
            }
            const data = await response.json();
            setTeamData(data);
            setIsTeamData(true);
        } catch (error) {
            console.error(error);
        }
    }, [team]);

    return (
        <>
        <div>
            <h1 className='title'>
                Witaj na stronie z danymi z Premier League!
            </h1>
        </div>
        <div className='page-conteiner'>
            <h3 className='title'>Formularze:</h3>
            <p>Podaj nazwę zespołu:</p>
            <input onChange={handleChangeTeam} />
            <br />
            <button type="submit" onClick={handleButtonClick}>Zatwierdź</button>
            {isTeamData && <TeamPage teamData={teamData} isTeamData={isTeamData}/>}
        </div>
        </>
    );
};

export default MainPage;
