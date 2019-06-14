/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMarathonLoopRewardDetailComponent } from 'app/entities/m-marathon-loop-reward/m-marathon-loop-reward-detail.component';
import { MMarathonLoopReward } from 'app/shared/model/m-marathon-loop-reward.model';

describe('Component Tests', () => {
  describe('MMarathonLoopReward Management Detail Component', () => {
    let comp: MMarathonLoopRewardDetailComponent;
    let fixture: ComponentFixture<MMarathonLoopRewardDetailComponent>;
    const route = ({ data: of({ mMarathonLoopReward: new MMarathonLoopReward(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMarathonLoopRewardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MMarathonLoopRewardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMarathonLoopRewardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mMarathonLoopReward).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
