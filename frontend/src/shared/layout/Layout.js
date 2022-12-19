import {NavLink, Outlet} from 'react-router-dom';
import './layout.css';

const Layout = () => {
    return (<>
        <div className="header">
            <div className="header-right">
                <NavLink to="/show">Show</NavLink>
                <NavLink to="/recognition">Recognition</NavLink>
            </div>
        </div>

        <Outlet/>

        <footer>
        </footer>
    </>);
};

export {Layout};
