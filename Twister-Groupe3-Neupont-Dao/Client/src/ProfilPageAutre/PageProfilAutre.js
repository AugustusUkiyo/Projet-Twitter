import React, { Component } from 'react';
import './page_profil_autre.css';
import Logo from '../Logo';
import ZoneRecherche from '../ZoneRecherche';
import Logout from '../Logout';
import StatsProfilAutre from './StatsProfilAutre';
import ListFriendAutre from './ListFriendAutre';
import ListMessageProfilAutre from './ListMessageProfilAutre';

class PageProfilAutre extends Component{
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
		<div id="div_principal_pro_autre">

		<div id="header_pro_autre">
		<Logo gbTwisterPage={this.props.gbTwisterPage}/>
		<ZoneRecherche search={this.props.search}/>
	    	<Logout cle={this.state.cle} logout={this.props.logout} />
		</div>

		<div id="body_pro_autre">
		<div id="bloc1_pro_autre">
		<StatsProfilAutre cle={this.state.cle} id_co={this.props.id_co} id_pro={this.props.id_pro}/>
		<ListFriendAutre cle={this.state.cle} id_pro={this.props.id_pro} profil={this.props.profil}/>
		<p id="nom_site_pro_autre">Twister</p>
		</div>
		<ListMessageProfilAutre cle={this.state.cle} id_pro={this.props.id_pro}/>
	    </div>	    	    
	    
	    </div>
		</body>
		</html>);

    }
}

export default PageProfilAutre;

