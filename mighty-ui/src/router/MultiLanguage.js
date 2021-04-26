import axios from "axios";

const LANGUAGE_PATH = "/api/language";
const LANGAUGE_CODE = "EN";

let langText = "";

const language = (keyword) => {
    axios
        .post(LANGUAGE_PATH, {
            keyword: keyword,
            code: LANGAUGE_CODE,
        })
        .then(
            (response) => (
                (langText = ""),
                (langText = response.data)
            )
        )
        .catch((error) => alert(keyword + "/" + code + "-" + error));
}

export { langText, language }