import React, { useState } from "react";
import { Button, Backdrop, CircularProgress } from '@mui/material';
import statisticsController from "../controllers/StatisticsController";

async function updateStatistics() {
    const data = await statisticsController().refreshStatistics()
        .then((response) => {
            return response.data;
        })
        .catch((err) => {
            return err.response;
        });

    return data;
}

function downloadStatisticsAsHtml() {
    return statisticsController().downloadAsHtml()
        .then((responce) => {
            return responce.data;
        })
        .catch((err) => {
            return err.response;
        });
}

export default function StatisticsFeatures() {
    const [isLoading, setLoading] = useState(false);

    const updateTournaments = async () => {
        setLoading(true);
        await updateStatistics();
        setLoading(false);
    }

    return (
        <div>
            <div style={{
                display: 'flex',
                justifyContent: 'space-between',
                width: '30%',
                marginLeft: '4rem'
            }}>
                <Button onClick={updateTournaments} variant="contained" color="secondary">Update Tournaments</Button>
                <Button onClick={downloadStatisticsAsHtml} download variant="contained" color="secondary">Download as HTML</Button>
            </div>
            <div>
                <Backdrop
                    sx={{ color: '#fff', zIndex: (theme) => theme.zIndex.drawer + 1 }}
                    open={isLoading}
                >
                    <CircularProgress color="inherit" />
                </Backdrop>
            </div>
        </div>
    )
}