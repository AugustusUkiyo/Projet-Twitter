import React, { Component } from 'react';
import Login from './Login';
import TwisterPage from './TwisterPage/TwisterPage';
import PageProfil from './ProfilPage/PageProfil';
import PageProfilAutre from './ProfilPageAutre/PageProfilAutre';
import SearchPage from './Search/SearchPage';

class NavigationPannel extends Component{
    constructor(props){
	super(props);
    }

    render(){
	if(this.props.isConnected){
	    if(this.props.pc === "TwisterProfil"){
		if(this.props.id_co == this.props.id_pro){
		    return (<nav>
			    <PageProfil cle={this.props.cle} id_co={this.props.id_co} logout={this.props.logout} profil={this.props.profil} gbTwisterPage={this.props.gbTwisterPage} search={this.props.search}/>
			</nav>);
		}
		else{
		    return (<nav>
			    <PageProfilAutre cle={this.props.cle} id_co={this.props.id_co} id_pro={this.props.id_pro} logout={this.props.logout} profil={this.props.profil} gbTwisterPage={this.props.gbTwisterPage} search={this.props.search}/>
			</nav>);
		}
	    }
	    else{
	    	if(this.props.pc === "SearchPage"){
	    		return (<nav>
				<SearchPage cle={this.props.cle} search={this.props.search} text_search={this.props.text_search} id_co={this.props.id_co} logout={this.props.logout} profil={this.props.profil} gbTwisterPage={this.props.gbTwisterPage}/>
				</nav>);
	    	}
	    	else{
			return (<nav>
				<TwisterPage cle={this.props.cle} id_co={this.props.id_co} logout={this.props.logout} profil={this.props.profil} gbTwisterPage={this.props.gbTwisterPage} search={this.props.search}/>
				</nav>);	
	    	}
	    }
	}
	else{
	    return (<nav>
		    <Login sign={this.props.sign} login={this.props.login} />
		    </nav>);
	}
    }
}

export default NavigationPannel;
