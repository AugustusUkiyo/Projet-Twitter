import React, { Component } from 'react';
import './page_profil.css';
import Logo from '../Logo';
import ZoneRecherche from '../ZoneRecherche';
import Logout from '../Logout';
import StatsProfil from './StatsProfil';
import ListFriend from './ListFriend';
import ListMessageProfil from './ListMessageProfil';

class PageProfil extends Component{
    constructor(props){
	super(props);
	this.state = {cle : this.props.cle};
    }

    render(){
	return (
		<html>
		<head>
		<title> Page profil </title>
		</head>
		<body>
		<div id="div_principal_pro">

		<div id="header_pro">
		<Logo gbTwisterPage={this.props.gbTwisterPage}/>
		<ZoneRecherche search={this.props.search}/>
	    	<Logout cle={this.state.cle} logout={this.props.logout} />
		</div>

		<div id="body_pro">
		<div id="bloc1_pro">
		<StatsProfil cle={this.state.cle} id_co={this.props.id_co}/>
		<ListFriend cle={this.state.cle} id_co={this.props.id_co} profil={this.props.profil}/>
		<p id="nom_site_pro">Twister</p>
		</div>
		<ListMessageProfil cle={this.state.cle} id_co={this.props.id_co}/>
	    </div>
		
	    </div>
		</body>
		</html>);

    }
}

export default PageProfil;

