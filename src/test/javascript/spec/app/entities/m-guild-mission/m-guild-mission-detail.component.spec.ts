/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGuildMissionDetailComponent } from 'app/entities/m-guild-mission/m-guild-mission-detail.component';
import { MGuildMission } from 'app/shared/model/m-guild-mission.model';

describe('Component Tests', () => {
  describe('MGuildMission Management Detail Component', () => {
    let comp: MGuildMissionDetailComponent;
    let fixture: ComponentFixture<MGuildMissionDetailComponent>;
    const route = ({ data: of({ mGuildMission: new MGuildMission(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGuildMissionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MGuildMissionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGuildMissionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mGuildMission).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
