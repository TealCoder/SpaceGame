class Animation {
  constructor()
  {
    this.frames = [];
    this.current_frame = 0;
  }

  getFrame()
  {
    if (this.current_frame >= this.frames.length)
      this.current_frame = 0;
    return this.frames[this.current_frame++];
  }

  addFrame(frame)
  {
    this.frames.push(frame);
  }
}
