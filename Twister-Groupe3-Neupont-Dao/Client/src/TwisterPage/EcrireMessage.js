import React, { Component } from 'react';
import axios from 'axios';
import './page_principale.css';

class EcrireMessage extends Component{
    constructor(props){
	super(props);
	this.state = {text : "hello" , cle : this.props.cle, status : "" , msg_error : ""};
	this.send = this.send.bind(this);
	this.check = this.check.bind(this);
    }

    check(reponse){
	if(reponse.data["status"] == "Error"){
	    this.setState({status : "error", msg_error : reponse.data["Erreur"]});
	}
	else{
	    {this.setState({status : "", text :"hello1"});this.refs.text_msg.value = "";}
	}
    }
    
    send(){
	const formData = new URLSearchParams();
	formData.append("key",this.state.cle);
	formData.append("text",this.refs.text_msg.value);
	axios.get('http://localhost:8080/TwisterGN&DH/Message/AddMessage?'+formData).then(reponse => {this.check(reponse);
												      this.props.recharge();
												     });
    }
    
    render(){
	return (
		<div id="nouveau_message">
		{this.state.msg_error === "Too much letters" ? alert("Vous avez rentré trop de caractères, la limite est de 300.") : ""}
	    {this.state.msg_error === "Too much letters" ? this.state.msg_error = "" : ""}
		<span id="text_ecrire_msg">Ecrire un message...</span>
		<textarea ref="text_msg" id="nv_msg"></textarea>
		<button className='button' id="nv_msg_submit" onClick={this.send}> Envoyer </button>
		</div>
	);
    }
}

export default EcrireMessage;
