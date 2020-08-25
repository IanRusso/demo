import axios from 'axios';

//need to proxy these requests to http://172.29.186.113:8080
const OECD_REST = "/rest/oecd";
const COUNTRY_NAMES = "/countryNames";
const VARIABLE_NAMES = "/variableNames";
const DATASETS = "/datasets";

class OecdService {
    static getCountryNames() {
        return axios.get(OECD_REST + COUNTRY_NAMES);
    }

    static getVariableNames() {
        return axios.get(OECD_REST + VARIABLE_NAMES);
    }

    static getDatasets() {
        return axios.get(OECD_REST + DATASETS);
    }
}

export default OecdService;