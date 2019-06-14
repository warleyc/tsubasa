/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMarathonRankingRewardDetailComponent } from 'app/entities/m-marathon-ranking-reward/m-marathon-ranking-reward-detail.component';
import { MMarathonRankingReward } from 'app/shared/model/m-marathon-ranking-reward.model';

describe('Component Tests', () => {
  describe('MMarathonRankingReward Management Detail Component', () => {
    let comp: MMarathonRankingRewardDetailComponent;
    let fixture: ComponentFixture<MMarathonRankingRewardDetailComponent>;
    const route = ({ data: of({ mMarathonRankingReward: new MMarathonRankingReward(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMarathonRankingRewardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MMarathonRankingRewardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMarathonRankingRewardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mMarathonRankingReward).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
