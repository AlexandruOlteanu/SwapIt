import React, { Component } from "react";

class LocationSection extends Component {
    constructor(props) {
        super(props);
        this.mapRef = React.createRef();
    }

    componentDidMount() {
        // Initialize the map
        const map = new window.google.maps.Map(this.mapRef.current, {
            center: { lat: 44.435947, lng: 26.048328 },
            zoom: 13
        });

        // Add a marker to the map
        const marker = new window.google.maps.Marker({
            position: { lat: 44.435947, lng: 26.048328 },
            map: map,
            title: "Bucharest, Romania"
        });
    }

    render() {
        return <div className="w-95 mb-4 br-10" ref={this.mapRef} style={{ height: "0", paddingBottom: "75%" }} />;
    }
}

export default LocationSection;
