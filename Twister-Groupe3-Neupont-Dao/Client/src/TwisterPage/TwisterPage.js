import React, { Component } from 'react';
import './page_principale.css';
import Logo from '../Logo';
import ZoneRecherche from '../ZoneRecherche';
import Logout from '../Logout';
import Stats from './Stats';
import EcrireMessage from './EcrireMessage';
import ListMessage from './ListMessage';

class TwisterPage extends Component{
    constructor(props){
	super(props);
	this.state = {cle : this.props.cle , recharge : ""};
	this.demande_recharge = this.demande_recharge.bind(this);
    }

    demande_recharge(){
	this.setState({cle : this.state.cle , recharge : ""});
    }
    
    render(){
	return (
		<html>
		<head>
		<title> Page principale </title>
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
		<EcrireMessage cle={this.state.cle} recharge={this.demande_recharge}/>
		<ListMessage cle={this.state.cle} profil={this.props.profil}/>
		</div>
		</div>
		</div>
		</body>
		</html>
	);
    }
}

export default TwisterPage;

