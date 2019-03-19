import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {MainComponent} from './component/main/main.component';
import {RouterModule, Routes} from "@angular/router";
import {HttpClientModule} from "@angular/common/http";
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatProgressSpinnerModule} from '@angular/material';
const appRoutes: Routes = [
  {path: '', component: MainComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    MainComponent
  ],
  imports: [
    MatProgressSpinnerModule,
    MatCardModule,
    MatButtonModule,
    MatToolbarModule,
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(
      appRoutes,
      {enableTracing: false} // <-- debugging purposes only
    )
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
