import {Component, OnInit} from '@angular/core';
import {SoberRobotService} from "../../service/sober-robot/sober-robot.service";

@Component({
    selector: 'app-main',
    templateUrl: './main.component.html',
    styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
    selectedFile: File[] = [];
    normalResults = [];
    drunkResults = [];
    dangerResults = [];
    loading = false;

    constructor(private soberRobotService: SoberRobotService) {
    }

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
        this.selectedFile = [];
        this.loading = true;
        this.soberRobotService.upload(fd).subscribe(
            res => {
                this.normalResults = res['normal'];
                this.drunkResults = res['drunk'];
                this.dangerResults = res['danger'];
                console.log(this.dangerResults);
                console.log(this.drunkResults);
                console.log(this.normalResults);
                this.loading = false;
            },
            err => {
                console.log(err);
                this.loading = false;
            }
        );
    }
}
