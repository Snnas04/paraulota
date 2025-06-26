import { Routes } from '@angular/router'
import { ConfigurationComponent } from '@pages/configuration/configuration.component'
import { GameComponent } from '@pages/game/game.component'
import { HomeComponent } from '@pages/home/home.component'
import { NotFoundComponent } from '@pages/not-found/not-found.component'
import { SearchComponent } from '@pages/search/search.component'

export const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'game', component: GameComponent },
  { path: 'configuration', component: ConfigurationComponent },
  { path: 'search', component: SearchComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: '**', component: NotFoundComponent },
]
