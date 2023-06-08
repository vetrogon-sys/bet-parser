import React from 'react';
import './App.css';
import StatisticTable from "./components/StatisticTable";
import StatisticsFeatures from "./components/StatisticsFeaturesComponent";

const App = () => {
  return (
    <div className="App">
      <StatisticTable />
      <StatisticsFeatures />
    </div>
  );
}

export default App;
