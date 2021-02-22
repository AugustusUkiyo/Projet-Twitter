import React, { Component } from 'react';
import './page_search.css';
import Logo from '../Logo';
import ZoneRecherche from '../ZoneRecherche';
import Logout from '../Logout';
import Stats from '../TwisterPage/Stats';
import Search from './Search';

class SearchPage extends Component{
    constructor(props){
	super(props);
	this.state = {cle : this.props.cle};
    }
    
    render(){
	return (
		<html>
		<head>
		<title> Page de recherche </title>
		</head>
		<body>
		<div id="div_principal">		
		<div id="header">
		<Logo gbTwisterPage={this.props.gbTwisterPage}/>
		<ZoneRecherche search={this.props.search}/>
	    	<Logout cle={this.state.cle} logout={this.props.logout} />
		</div>
	    <div id="body">
		<Stats cle={this.state.cle} id_co={this.props.id_co} profil={this.props.profil}/>
		<div id="reste">
		<Search cle={this.props.cle} text_search={this.props.text_search} profil={this.props.profil}/>
		</div>
		</div>
		</div>
		</body>
		</html>
	);
    }
}

export default SearchPage;
