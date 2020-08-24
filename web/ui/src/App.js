import React from 'react';
import logo from './logo.svg';
import './App.css';
import OecdService from './common/services/OecdService';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn Test React
        {displayData()}
        </a>
      </header>
    </div>
  );

  function displayData() {
    console.log("Attempting to get country names...")
    OecdService.getCountryNames().then(result => console.log(result)).catch(e => console.log(e)).finally();
    return "test";
    //return OecdService.getCountryNames().then().catch().finally();
}
  
}

export default App;
