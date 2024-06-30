import React from 'react';

const PlayerStats = (playerData) => {
    return (
        <div>
            <p>Występy: <b>{playerData.playerData.appearences}</b></p>
            <p>Minuty na boisku: <b>{playerData.playerData.minutes}</b></p>
            <p>Najlepsza ocena: <b>{playerData.playerData.best_rating}</b></p>
            <p>Średnia ocen: <b>{playerData.playerData.rating}</b></p>
            <p>Strzały: <b>{playerData.playerData.shots}</b></p>
            <p>Gole: <b>{playerData.playerData.goals}</b></p>
            <p>Asysty: <b>{playerData.playerData.assists}</b></p>
            <p>Próby podań: <b>{playerData.playerData.passes}</b></p>
            <p>Celne podania: <b>{playerData.playerData.accuracy}</b></p>
            <p>Procent celnych podań: <b>{playerData.playerData.accuracy_passes_percent}%</b></p>
            <p>Próby dryblingu: <b>{playerData.playerData.dribbles_attempts}</b></p>
            <p>Udane dryblingi: <b>{playerData.playerData.dribbles_success}</b></p>
            <p>Procent udanych dryblingów: <b>{playerData.playerData.dribbles_success_percent}%</b></p>
            <p>Żółte kartki: <b>{playerData.playerData.yellow_cards}</b></p>
            <p>Czerwone kartki: <b>{playerData.playerData.red_cards}</b></p>
        </div>
    );
};

export default PlayerStats;
