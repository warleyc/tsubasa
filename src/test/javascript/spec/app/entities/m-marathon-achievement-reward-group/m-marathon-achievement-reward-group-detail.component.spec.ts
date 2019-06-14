/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMarathonAchievementRewardGroupDetailComponent } from 'app/entities/m-marathon-achievement-reward-group/m-marathon-achievement-reward-group-detail.component';
import { MMarathonAchievementRewardGroup } from 'app/shared/model/m-marathon-achievement-reward-group.model';

describe('Component Tests', () => {
  describe('MMarathonAchievementRewardGroup Management Detail Component', () => {
    let comp: MMarathonAchievementRewardGroupDetailComponent;
    let fixture: ComponentFixture<MMarathonAchievementRewardGroupDetailComponent>;
    const route = ({ data: of({ mMarathonAchievementRewardGroup: new MMarathonAchievementRewardGroup(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMarathonAchievementRewardGroupDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MMarathonAchievementRewardGroupDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMarathonAchievementRewardGroupDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mMarathonAchievementRewardGroup).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
