import React, { useEffect, useState } from 'react';
import { TableContainer, Table, TableTypeMap, TableHead, TableRow, TableCell, TableBody, TablePagination } from '@mui/material';
import { Backdrop, CircularProgress } from '@mui/material';
import statisticsController from "../controllers/StatisticsController";

async function getStatisticsPage(currentPage, pageSize) {
    const data = await statisticsController().findAll(currentPage, pageSize)
        .then((response) => {
            return response.data;
        })
        .catch((err) => {
            return err.response;
        });

    return data;
}

const columns = [
    { id: 'id', label: 'Id' },
    { id: 'tournament', label: 'Tournament' },
    { id: 'teams', label: 'Teams' },
    { id: 'dateStart', label: 'Date start' },
    { id: 'sportType', label: 'Sport' },
    { id: 'link', label: 'Link' }
]

export default function StatisticTable() {
    const [statistics, setStatistics] = useState(null);
    const [currentPage, setCurrentPage] = useState(0);
    const [pageSize, setPageSize] = useState(25);
    const [totalElements, setTotalElements] = useState(0);
    const [isLoading, setLoading] = useState(false);

    useEffect(() => {
        const fetchData = async () => {
            setLoading(true);
            const statistics = await getStatisticsPage(currentPage, pageSize);
            setLoading(false);

            setStatistics(statistics.content);
            setTotalElements(statistics.totalElements);
        };

        fetchData();
    }, [currentPage, pageSize]);

    const handleChangePage = (event, newPage) => {
        setCurrentPage(newPage);
    };

    const handleChangeRowsPerPage = (event) => {
        setPageSize(+event.target.value);
        setCurrentPage(0);
    };

    return (
        <div>
            <TableContainer sx={{ maxHeight: 440 }}>
                <Table stickyHeader aria-label="sticky table">
                    <TableHead>
                        <TableRow>
                            {columns.map((column) => (
                                <TableCell
                                    key={column.id}
                                >
                                    {column.label}
                                </TableCell>
                            ))}
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {statistics ?
                            statistics
                                .map((row) => {
                                    return (
                                        <TableRow hover role="checkbox" tabIndex={-1} key={row.id}>
                                            {columns.map((column) => {
                                                const value = row[column.id];
                                                return (
                                                    <TableCell key={column.id}>
                                                        {value}
                                                    </TableCell>
                                                );
                                            })}
                                        </TableRow>
                                    );
                                }) : null}
                    </TableBody>
                </Table>
            </TableContainer>
            <TablePagination
                rowsPerPageOptions={[10, 25, 100]}
                component="div"
                count={totalElements}
                rowsPerPage={pageSize}
                page={currentPage}
                onPageChange={handleChangePage}
                onRowsPerPageChange={handleChangeRowsPerPage}
            />

            <Backdrop
                sx={{ color: '#fff', zIndex: (theme) => theme.zIndex.drawer + 1 }}
                open={isLoading}
            >
                <CircularProgress color="inherit" />
            </Backdrop>
        </div>
    );
}
