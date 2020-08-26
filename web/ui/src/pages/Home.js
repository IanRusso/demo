import React, { useState, useEffect } from 'react';
import OecdService from '../common/services/OecdService';
import _ from "lodash";

export default function Home() {

    const ONE_PERCENT_WEALTH = "yearToOnePercentWealth";
    const FIVE_PERCENT_WEALTH = "yearToFivePercentWealth";
    const TEN_PERCENT_WEALTH = "yearToTenPercentWealth";
    const INDEBTED_HOUSEHOLDS = "yearToPercentHouseholdsIndebted";

    const [countryNames, setCountryNames] = useState([]);
    const [displayData, setDisplayData] = useState({});
    const [displayDataLoaded, setDisplayDataLoaded] = useState(false);

    useEffect(fetch, [])

    return (
        <div>
            {countryNames.map(country => (
                <div key={country} style={{border: "solid 1px white", padding: "3px"}}>
                    <h4>{country}</h4>
                    {displayDataLoaded ? "" : getDisplayData(country)}
                </div>
            ))}
        </div>
    );

    function getDisplayData(countryName) {
        const countryData = displayData[countryName];
        if (!countryData) {
            //console.log("Country data not found for country -" + countryName)
            //console.log(displayData)
            return "";
        }
        //console.log("Showing display data for country - " + countryName);
        const datasets = [
            [ONE_PERCENT_WEALTH],
            displayData[countryName][FIVE_PERCENT_WEALTH],
            displayData[countryName][TEN_PERCENT_WEALTH],
            displayData[countryName][INDEBTED_HOUSEHOLDS]
        ]
        return (
            <div>
                {datasets.forEach(dataset => (
                    <div>
                        {Object.keys(dataset).forEach(k => (
                            <div key={k}>
                                {k} : {dataset[k]}
                            </div>
                        ))}
                    </div>
                ))}
            </div>
        );
    }

    function fetch() {
        console.log("Attempting to get country names...")
        OecdService.getCountryNames()
            .then(response => {
                setCountryNames(response.data)
                response.data.forEach(countryName => {
                    OecdService.getDisplayData(countryName)
                        .then(response => {
                            console.log("Previous display data - ", displayData)
                            const newDisplayData = _.cloneDeep(displayData);
                            newDisplayData[countryName] = response.data;
                            console.log("New display data - ", newDisplayData)
                            setDisplayData(newDisplayData);
                            if (Object.keys(response.data).indexOf(countryName) === Object.keys(response.data).length) {
                                setDisplayDataLoaded(true);
                            } 
                        })
                        .catch(e => console.log(e));
                })
            })
            .catch(e => console.log(e));
    }
}