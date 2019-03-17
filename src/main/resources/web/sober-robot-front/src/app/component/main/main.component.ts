import { Component, OnInit } from '@angular/core';
import {SoberRobotService} from "../../service/sober-robot/sober-robot.service";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  selectedFile: File[] = null;
  result = null;
  constructor(private soberRobotService: SoberRobotService) { }

  ngOnInit() {
  }

  onFileSelected(event) {
    this.selectedFile = <File[]>event.target.files;
  }

  onUpload() {
    const fd = new FormData();
    for (let i = 0; i < this.selectedFile.length; i++) {
      fd.append('images', this.selectedFile[i], this.selectedFile[i].name);
    }
    this.soberRobotService.upload(fd).subscribe(
      res => this.result = res,
      err => console.log(err)
    )
  }
}
