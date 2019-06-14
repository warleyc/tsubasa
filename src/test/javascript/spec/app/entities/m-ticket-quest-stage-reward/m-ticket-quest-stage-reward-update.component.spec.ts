/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTicketQuestStageRewardUpdateComponent } from 'app/entities/m-ticket-quest-stage-reward/m-ticket-quest-stage-reward-update.component';
import { MTicketQuestStageRewardService } from 'app/entities/m-ticket-quest-stage-reward/m-ticket-quest-stage-reward.service';
import { MTicketQuestStageReward } from 'app/shared/model/m-ticket-quest-stage-reward.model';

describe('Component Tests', () => {
  describe('MTicketQuestStageReward Management Update Component', () => {
    let comp: MTicketQuestStageRewardUpdateComponent;
    let fixture: ComponentFixture<MTicketQuestStageRewardUpdateComponent>;
    let service: MTicketQuestStageRewardService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTicketQuestStageRewardUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MTicketQuestStageRewardUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MTicketQuestStageRewardUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTicketQuestStageRewardService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MTicketQuestStageReward(123);
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
        const entity = new MTicketQuestStageReward();
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
