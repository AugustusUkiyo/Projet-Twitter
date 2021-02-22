import React, { Component } from 'react';
import axios from 'axios';
import './TwisterPage/page_principale.css';

class Logout extends Component{
    constructor(props){
	super(props);
	this.send = this.send.bind(this);
    }

    send(){
	const formData = new URLSearchParams();
	formData.append("key",this.props.cle);
	axios.get("http://localhost:8080/TwisterGN&DH/User/Logout?"+formData).then(reponse => {this.props.logout();});	
    }
    
    render(){
	return (
		<div id="lien_deconnexion">
		<input type="submit" value="Deconnexion" id="deco" onClick={this.send} />
		</div>	
	);

    }
}

export default Logout;
