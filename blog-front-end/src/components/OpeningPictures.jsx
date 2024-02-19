import React from "react";
import snow_rotunda from '../images/snow-rotunda.jpg';
import autumn_leaves from '../images/autumn-leaves.jpg';


export const OpeningPictures = () => {
    return (
        <div className="opening-pics">
            <img src={snow_rotunda} alt="rotunda" />
            <img src={autumn_leaves} alt="leaves" />
        </div>
    )
}