/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestDropRateCampaignContentUpdateComponent } from 'app/entities/m-quest-drop-rate-campaign-content/m-quest-drop-rate-campaign-content-update.component';
import { MQuestDropRateCampaignContentService } from 'app/entities/m-quest-drop-rate-campaign-content/m-quest-drop-rate-campaign-content.service';
import { MQuestDropRateCampaignContent } from 'app/shared/model/m-quest-drop-rate-campaign-content.model';

describe('Component Tests', () => {
  describe('MQuestDropRateCampaignContent Management Update Component', () => {
    let comp: MQuestDropRateCampaignContentUpdateComponent;
    let fixture: ComponentFixture<MQuestDropRateCampaignContentUpdateComponent>;
    let service: MQuestDropRateCampaignContentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestDropRateCampaignContentUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MQuestDropRateCampaignContentUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MQuestDropRateCampaignContentUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MQuestDropRateCampaignContentService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MQuestDropRateCampaignContent(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new MQuestDropRateCampaignContent();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
