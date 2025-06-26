import { Component } from '@angular/core';
import { SettingsService } from '@services/settings.service';
import { NavigationService } from '@services/navigation.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-nav-bar',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css',
})
export class NavBarComponent {
  title: string[] = ['P','A','R','A','U','L','O','T','A'] 

  constructor(public settings: SettingsService, public navigation: NavigationService) {}
}
