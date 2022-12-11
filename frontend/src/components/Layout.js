import {Outlet, NavLink, Link} from 'react-router-dom';
import logo from '../icons/logo.svg';

const Layout = () => {
	return (<>
		<div className="header">
			<Link to="/" className="logo">
				<img src={logo} height={30} alt="logo"/>
			</Link>
			<div className="header-right">
				<NavLink to="/show">Show</NavLink>
				<NavLink to="/">Wiki</NavLink>
				<NavLink to="/recognition">Recognition</NavLink>
			</div>
		</div>

		<Outlet/>

		<footer>
		</footer>
	</>);
};

export {Layout};
