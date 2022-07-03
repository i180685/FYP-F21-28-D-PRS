import React from "react";
import "./homeScreen.css"
import 'react-pro-sidebar/dist/css/styles.css';
import Videoleft from "../assets/videos/leftSide.mp4"
import VideoRight from "../assets/videos/rightSide.mp4"
import LeftNight from "../assets/videos/leftNight.mp4"
import RightNight from "../assets/videos/rightNight.mp4"

const Camera = () => {
    return (
        <div className="p-0 m-0">
            <div className="headings-spot">
                SECURITY VIDEO FEED
            </div>
            <div className="videoLeft">
                <video controls autostart autoPlay loop src={Videoleft} width="550" style={{}} type="video/mp4" />
            </div>
            <div className="videoRight">
                <video controls autostart autoPlay loop src={VideoRight} width="550" type="video/mp4" />
            </div>
            
            <div className="videoContent headings" >Camera 1</div>
            <div className="videoContent headings" style={{marginLeft:580}}>Camera 2</div>
            
        </div>

    );
};

export default Camera;

