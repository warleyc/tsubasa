/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMarathonBoostScheduleDetailComponent } from 'app/entities/m-marathon-boost-schedule/m-marathon-boost-schedule-detail.component';
import { MMarathonBoostSchedule } from 'app/shared/model/m-marathon-boost-schedule.model';

describe('Component Tests', () => {
  describe('MMarathonBoostSchedule Management Detail Component', () => {
    let comp: MMarathonBoostScheduleDetailComponent;
    let fixture: ComponentFixture<MMarathonBoostScheduleDetailComponent>;
    const route = ({ data: of({ mMarathonBoostSchedule: new MMarathonBoostSchedule(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMarathonBoostScheduleDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MMarathonBoostScheduleDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMarathonBoostScheduleDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mMarathonBoostSchedule).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
