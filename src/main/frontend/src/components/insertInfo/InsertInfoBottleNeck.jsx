import React, { useEffect, useState } from "react";
import axios from "axios";
import styles from "./category.module.css"
import {Link} from "react-router-dom";
import {buildStyles, CircularProgressbar} from "react-circular-progressbar";
// import { CircularProgressbar } from 'react-circular-progressbar';
import 'react-circular-progressbar/dist/styles.css';
import { easeQuadInOut } from "d3-ease";
import AnimatedProgressProvider from "./AnimatedProgressProvider";
import ChangingProgressProvider from "./ChangingProgressProvider";


function InsertInfoBottleNeck() {
    const [postData, setPostData] = useState({
        cpuName: '',
        gpuName: ''
    });
    const [bottleNeck, setBottleNeck] = useState([]);
    const path = window.location.href;
    const parts = path.split('/');
    const lastPart = parts[parts.length - 1];

    const percentage = 66;

    useEffect(() => {
        if(lastPart === "MySpec"){
            console.log("hi1");
            const cpuData = localStorage.getItem('cpuData');
            const gpuData = localStorage.getItem('gpuData');
            setPostData({ cpuName: cpuData, gpuName: gpuData });
        }
        else if(lastPart === "SelectSpec"){
            console.log("hi2");
            const cpuData = localStorage.getItem('selectCpuData');
            const gpuData = localStorage.getItem('selectGpuData');
            setPostData({ cpuName: cpuData, gpuName: gpuData });
        }
    }, []);

    useEffect(() => {
        if (postData.cpuName && postData.gpuName) {
            const fetchData = async () => {
                try {
                    const response = await axios.post('/selectedBottleNeck', postData);
                    setBottleNeck(response.data);
                } catch (error) {
                    console.log(error);
                }
            };
            fetchData();
        }
    }, [postData]);

    return (
        <>
            <div className={styles.bottleNeckComp}>
                <div className={styles.bottleNeckText}>
                <p className={styles.bottleNeckTitle}>CALCULATOR RESULT</p>
                    {bottleNeck.cpuBottleNeckValue-bottleNeck.gpuBottleNeckValue>5 &&(
                        <p> 작업을 실행할 때 {bottleNeck.cpuInfo}는 {bottleNeck.gpuInfo}에 비해 약합니다.</p>
                        )}
                    {bottleNeck.gpuBottleNeckValue-bottleNeck.cpuBottleNeckValue>5 &&(
                        <p> 작업을 실행할 때 {bottleNeck.gpuInfo}는 {bottleNeck.cpuInfo}에 비해 약합니다.</p>
                    )}
                        <p>이 구성에서는 {Math.abs(bottleNeck.cpuBottleNeckValue-bottleNeck.gpuBottleNeckValue)}%의 병목 현상이 있습니다.</p>
                </div>
                <div className={styles.CircularChart}>
                    {/*<CircularProgressbar*/}
                    {/*    value={Math.abs(bottleNeck.cpuBottleNeckValue-bottleNeck.gpuBottleNeckValue)}*/}
                    {/*    text={`${Math.abs(bottleNeck.cpuBottleNeckValue-bottleNeck.gpuBottleNeckValue)}%`}*/}
                    {/*    background*/}
                    {/*    backgroundPadding={6}*/}
                    {/*    styles={buildStyles({*/}
                    {/*        backgroundColor: "#3e98c7",*/}
                    {/*        textColor: "#fff",*/}
                    {/*        pathColor: "#fff",*/}
                    {/*        trailColor: "transparent"*/}
                    {/*    })}*/}
                    {/*    className={styles.circularProgressBar}*/}
                    {/*/>*/}
                            <CircularProgressbar
                                value={Math.abs(bottleNeck.cpuBottleNeckValue-bottleNeck.gpuBottleNeckValue)}
                                text={`${Math.abs(bottleNeck.cpuBottleNeckValue-bottleNeck.gpuBottleNeckValue)}%`}
                                circleRatio={0.5}
                                arcSweepAngle={180}
                                styles={buildStyles({
                                    rotation: 1 / 2 + 1 / 4,
                                    strokeLinecap: "butt",
                                    trailColor: "#eee"
                                })}
                            />
                </div>

            </div>

        </>
    );
}

export default InsertInfoBottleNeck;