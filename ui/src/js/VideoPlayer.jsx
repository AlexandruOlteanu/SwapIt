import React, { useRef, useEffect } from 'react';

function VideoPlayer({ videoToPlay, classes }) {
  const videoRef = useRef(null);

  useEffect(() => {
    videoRef.current.play();
    videoRef.current.setAttribute('loop', '');
    videoRef.current.removeAttribute('controls');
  }, []);

  return (
    <video ref={videoRef} className={classes} controls autoPlay muted>
      <source src={videoToPlay} type='video/mp4' />
    </video>
  );
}

export default VideoPlayer;