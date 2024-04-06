import React from "react";

function SpecialOffer() {

    const date = new Date();
    let day = date.getDate();
    let month = date.getMonth() + 1;
    let year = date.getFullYear();

    day -= 4;
    if (day < 1) {
        day = 1;
    }

    let firstDateStr = `${day}-${month}-${year}`;
    let daySecondDate = day + 6;
    let maximum = 31;
    if (month === 2) {
        maximum = 28;
    }
    else if (month === 2 || month === 4 || month === 6 || month === 9 || month === 11) {
        maximum = 30;
    }

    if (daySecondDate > maximum) {
        daySecondDate -= maximum;
        ++month;
    }
    if (month > 12) {
        month = 1;
        ++year;
    }
    let secondDateStr = `${daySecondDate}-${month}-${year}`;

    return (
        <div>
            Only from <span>{firstDateStr}</span> to <span>{secondDateStr}</span>
        </div>
    );
}

export default SpecialOffer