/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestDropRateCampaignContentDetailComponent } from 'app/entities/m-quest-drop-rate-campaign-content/m-quest-drop-rate-campaign-content-detail.component';
import { MQuestDropRateCampaignContent } from 'app/shared/model/m-quest-drop-rate-campaign-content.model';

describe('Component Tests', () => {
  describe('MQuestDropRateCampaignContent Management Detail Component', () => {
    let comp: MQuestDropRateCampaignContentDetailComponent;
    let fixture: ComponentFixture<MQuestDropRateCampaignContentDetailComponent>;
    const route = ({ data: of({ mQuestDropRateCampaignContent: new MQuestDropRateCampaignContent(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestDropRateCampaignContentDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MQuestDropRateCampaignContentDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MQuestDropRateCampaignContentDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mQuestDropRateCampaignContent).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
