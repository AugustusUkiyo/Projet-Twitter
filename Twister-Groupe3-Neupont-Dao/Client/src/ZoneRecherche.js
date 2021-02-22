import React, { Component } from 'react';
import './TwisterPage/page_principale.css';

class ZoneRecherche extends Component{
    constructor(props){
	super(props);
	this.send = this.send.bind(this);
    }

    send(){
    	this.props.search(this.refs.s.value);
    	this.refs.s.value = null;
    }

    render(){
		return (
			<div id="zone_recherche">
			<input type="text" ref="s" id="recherche"/>
			<button id="recherche_submit" onClick={this.send}>Recherche</button>
			</div>	    
		);
    }
}

export default ZoneRecherche;
