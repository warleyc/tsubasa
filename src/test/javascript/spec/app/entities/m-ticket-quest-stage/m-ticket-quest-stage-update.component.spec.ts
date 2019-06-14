/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTicketQuestStageUpdateComponent } from 'app/entities/m-ticket-quest-stage/m-ticket-quest-stage-update.component';
import { MTicketQuestStageService } from 'app/entities/m-ticket-quest-stage/m-ticket-quest-stage.service';
import { MTicketQuestStage } from 'app/shared/model/m-ticket-quest-stage.model';

describe('Component Tests', () => {
  describe('MTicketQuestStage Management Update Component', () => {
    let comp: MTicketQuestStageUpdateComponent;
    let fixture: ComponentFixture<MTicketQuestStageUpdateComponent>;
    let service: MTicketQuestStageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTicketQuestStageUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MTicketQuestStageUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MTicketQuestStageUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTicketQuestStageService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MTicketQuestStage(123);
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
        const entity = new MTicketQuestStage();
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
