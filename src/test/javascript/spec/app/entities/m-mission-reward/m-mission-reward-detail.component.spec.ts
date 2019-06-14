/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMissionRewardDetailComponent } from 'app/entities/m-mission-reward/m-mission-reward-detail.component';
import { MMissionReward } from 'app/shared/model/m-mission-reward.model';

describe('Component Tests', () => {
  describe('MMissionReward Management Detail Component', () => {
    let comp: MMissionRewardDetailComponent;
    let fixture: ComponentFixture<MMissionRewardDetailComponent>;
    const route = ({ data: of({ mMissionReward: new MMissionReward(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMissionRewardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MMissionRewardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMissionRewardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mMissionReward).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
