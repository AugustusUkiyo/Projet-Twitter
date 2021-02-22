import React, { Component } from 'react';
import axios from 'axios';
import './enregistrement.css';

class Signin extends Component{
    constructor(props){
	super(props);
	this.state = {status : "" , msg_error : ""};
	this.send = this.send.bind(this);
	this.reponse_login = this.reponse_login.bind(this);	
    }

    reponse_login(reponse){
	if(reponse.data["status"] == "Error"){
	    this.setState({status : "error" , msg_error : reponse.data["Erreur"]});
	}
	else{
	    this.setState({status : "" , msg_error : ""});
	    this.props.login();
	}
    }

    send(){
	const formData = new URLSearchParams();
	formData.append("nom",this.refs.nom.value);
	formData.append("prenom",this.refs.prenom.value);
	formData.append("login",this.refs.login.value);
	formData.append("password",this.refs.password.value);
	formData.append("password2",this.refs.password2.value);
	axios.get('http://localhost:8080/TwisterGN&DH/User/CreateUser?'+formData).then(reponse => {this.reponse_login(reponse);});
    }
    
    render(){
	return (
		<html>
		<head>
		<title> Enregistrement </title>
		</head>
		<body>
		
		<div class="d1">
		<h1 id="h1e">Enregistrement</h1>
		<div id="forme">
		
		<div class="d2">
		<span id="span1">Prenom</span>
		<span id="span2">Nom</span>
		</div>
		
		<div class="d3">
		<input type="text" ref="prenom" id="prenom"/>
		<input type="text" ref="nom" id="nom"/>
		</div>
		
		<div class="d4">
		<span id="spanlogin"> Login</span>
		<input type="text" ref="login" id="login"/>
		</div>
		<div class="d5">
		<span id="spanemail"> Email</span>
		<input type="text" id="email"/>
		</div>
		<div class="d6">
		<span id="spanmdp"> Password </span>
		<input type="password" ref="password" id="password"/>
		</div>
		<div class="d7">
		<span id="span mdp2"> Repeat </span>
		<input type="password" ref="password2" id="password2"/>
		</div>
		
		<div class="d8">
		<div class="ds1">
		<button className='button' id="s1" onClick={this.send}>Enregistrer</button>
		</div>
		<div class="ds2">
		<input type="submit" id="s2" onClick={this.props.annuler} value="Annuler"/>
		</div>
		</div>
		
	    </div>
		</div>
		{this.state.status === "error" ? <p id="p"> Erreur lors de la création : {this.state.msg_error}</p> : ""}
		</body>
		</html>

	);
    }
}

export default Signin;
