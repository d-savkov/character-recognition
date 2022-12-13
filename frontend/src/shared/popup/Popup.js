import './popup.css';

export default function Popup({active, setActive, children}) {

    return (
        <div className={active ? 'popup active' : 'popup'} onClick={() => setActive(false)}>
            <div className={active ? 'popup-content active' : 'popup-content'} onClick={e => e.stopPropagation()}>
                {children}
            </div>
        </div>
    );
}
