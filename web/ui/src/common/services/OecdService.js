import axios from 'axios';

const OECD_REST = "/rest/oecd";
const COUNTRY_NAMES = "/countryNames";
const VARIABLE_NAMES = "/variableNames";
const DATASETS = "/datasets";
const DISPLAY_DATA = "/displayData";

class OecdService {
    static getCountryNames() {
        return axios.get(OECD_REST + COUNTRY_NAMES);
    }

    static getDisplayData(countryName) {
        return axios.get(OECD_REST + DISPLAY_DATA + "/" + countryName.replace(" ", "%20"))
    }

    static getVariableNames() {
        return axios.get(OECD_REST + VARIABLE_NAMES);
    }

    static getDatasets() {
        return axios.get(OECD_REST + DATASETS);
    }
}

export default OecdService;