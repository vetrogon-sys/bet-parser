import React, { useEffect, useState } from 'react';
import { TableContainer, Table, TableSortLabel, Link, TableHead, TableRow, TableCell, TableBody, TablePagination, Box } from '@mui/material';
import { Backdrop, CircularProgress } from '@mui/material';
import { visuallyHidden } from '@mui/utils';
import statisticsController from "../controllers/StatisticsController";

async function getStatisticsPage(currentPage, pageSize, orderBy, direction) {
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
    {
        id: 'id',
        label: 'Id',
        format: (value) => value.toLocaleString('en-US'),
        sorted: true,
    },
    {
        id: 'tournament',
        label: 'Tournament',
        format: (value) => value.toLocaleString('en-US'),
    },
    {
        id: 'teams',
        label: 'Teams',
        format: (value) => value.toLocaleString('en-US'),
    },
    {
        id: 'dateStart',
        label: 'Date start',
        format: (value) => new Intl.DateTimeFormat('en-GB', { dateStyle: 'full', timeStyle: 'short' }).format(new Date(value)),
        sorted: true,
    },
    {
        id: 'sportType',
        label: 'Sport',
        format: (value) => value.toLocaleString('en-US'),
    },
    {
        id: 'link',
        label: 'Link',
        format: (value) => <Link href={value}>See site page</Link>,
    }
]

function descendingComparator(a, b, orderBy) {
    if (b[orderBy] < a[orderBy]) {
        return -1;
    }
    if (b[orderBy] > a[orderBy]) {
        return 1;
    }
    return 0;
}

function getComparator(order, orderBy) {
    return order === 'desc'
        ? (a, b) => descendingComparator(a, b, orderBy)
        : (a, b) => -descendingComparator(a, b, orderBy);
}

function stableSort(array, comparator) {
    const stabilizedThis = array.map((el, index) => [el, index]);
    stabilizedThis.sort((a, b) => {
        const order = comparator(a[0], b[0]);
        if (order !== 0) {
            return order;
        }
        return a[1] - b[1];
    });
    return stabilizedThis.map((el) => el[0]);
}

export default function StatisticTable() {
    const [statistics, setStatistics] = useState(null);
    const [currentPage, setCurrentPage] = useState(0);
    const [pageSize, setPageSize] = useState(25);
    const [totalElements, setTotalElements] = useState(0);
    const [isLoading, setLoading] = useState(false);
    const [orderBy, setOrderBy] = useState(null);
    const [order, setOrder] = useState(null);

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

    const handleRequestSort = (event, property) => {
        const isAsc = orderBy === property && order === 'asc';
        setOrder(isAsc ? 'desc' : 'asc');
        setOrderBy(property);
    };

    const createSortHandler = (property) => (event) => {
        handleRequestSort(event, property);

        setStatistics(stableSort(statistics, getComparator(order, orderBy)));
    };

    return (
        <div>
            <TableContainer sx={{ maxHeight: '80vh' }}>
                <Table stickyHeader aria-label="sticky table">
                    <TableHead>
                        <TableRow>
                            {columns.map((column) => (
                                <TableCell
                                    key={column.id}
                                    sortDirection={orderBy ? order : false}
                                >
                                    {column.sorted ?
                                        <TableSortLabel
                                            active={orderBy === column.id}
                                            direction={orderBy === column.id ? order : 'asc'}
                                            onClick={createSortHandler(column.id)}
                                        >
                                            {orderBy === column.id ? (
                                                <Box component="span" sx={visuallyHidden}>
                                                    {order === 'desc' ? 'sorted descending' : 'sorted ascending'}
                                                </Box>
                                            ) : null}
                                            {column.label}
                                        </TableSortLabel>
                                        : column.label
                                    }
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
                                                        {column.format
                                                            ? column.format(value)
                                                            : value}
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
