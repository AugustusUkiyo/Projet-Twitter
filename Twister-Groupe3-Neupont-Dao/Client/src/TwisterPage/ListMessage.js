import React, { Component } from 'react';
import axios from 'axios';
import './page_principale.css';

class ListMessage extends Component{
    constructor(props){
	super(props);
	this.state = {liste : []};
	this.chargeProfil = this.chargeProfil.bind(this);
    }

    componentDidMount(){
	const formData = new URLSearchParams();
	formData.append("key",this.props.cle);
	axios.get('http://localhost:8080/TwisterGN&DH/Message/ListAllMessage?'+formData).then(reponse =>
											      {
												  if(reponse.data["status"] === ""){
												      const posts = reponse.data["liste_messages"][0].slice(0,3);
												      this.setState({liste : posts});
												  }
												  else{
												      alert(reponse.data["Erreur"]);
												  }
											      });
    }

    chargeProfil(id){
	this.props.profil(id);
    }
    
    render(){
	return (
	    	<div id="liste_messages">
		{
		    this.state.liste.map((msg,index) => (
			    <div id="msg">
			    <div id="msg_nom">
			    <a href="#" onClick={() => {this.chargeProfil(msg["id"])}}>{msg["nom"]}</a>
			</div>
			    <div id="msg_date">
			    {msg["date"]}
			</div>
			    <div id="msg_text">
			    {msg["texte"]}
			</div>
			    </div>
			    
		    ))
		}
	    </div>
	)
    }
}

export default ListMessage;

