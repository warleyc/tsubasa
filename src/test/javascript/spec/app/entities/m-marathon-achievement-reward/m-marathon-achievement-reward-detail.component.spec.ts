/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMarathonAchievementRewardDetailComponent } from 'app/entities/m-marathon-achievement-reward/m-marathon-achievement-reward-detail.component';
import { MMarathonAchievementReward } from 'app/shared/model/m-marathon-achievement-reward.model';

describe('Component Tests', () => {
  describe('MMarathonAchievementReward Management Detail Component', () => {
    let comp: MMarathonAchievementRewardDetailComponent;
    let fixture: ComponentFixture<MMarathonAchievementRewardDetailComponent>;
    const route = ({ data: of({ mMarathonAchievementReward: new MMarathonAchievementReward(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMarathonAchievementRewardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MMarathonAchievementRewardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMarathonAchievementRewardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mMarathonAchievementReward).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
