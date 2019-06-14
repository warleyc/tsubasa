/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTicketQuestTsubasaPointRewardUpdateComponent } from 'app/entities/m-ticket-quest-tsubasa-point-reward/m-ticket-quest-tsubasa-point-reward-update.component';
import { MTicketQuestTsubasaPointRewardService } from 'app/entities/m-ticket-quest-tsubasa-point-reward/m-ticket-quest-tsubasa-point-reward.service';
import { MTicketQuestTsubasaPointReward } from 'app/shared/model/m-ticket-quest-tsubasa-point-reward.model';

describe('Component Tests', () => {
  describe('MTicketQuestTsubasaPointReward Management Update Component', () => {
    let comp: MTicketQuestTsubasaPointRewardUpdateComponent;
    let fixture: ComponentFixture<MTicketQuestTsubasaPointRewardUpdateComponent>;
    let service: MTicketQuestTsubasaPointRewardService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTicketQuestTsubasaPointRewardUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MTicketQuestTsubasaPointRewardUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MTicketQuestTsubasaPointRewardUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTicketQuestTsubasaPointRewardService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MTicketQuestTsubasaPointReward(123);
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
        const entity = new MTicketQuestTsubasaPointReward();
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
