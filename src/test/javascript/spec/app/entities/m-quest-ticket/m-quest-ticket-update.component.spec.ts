/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestTicketUpdateComponent } from 'app/entities/m-quest-ticket/m-quest-ticket-update.component';
import { MQuestTicketService } from 'app/entities/m-quest-ticket/m-quest-ticket.service';
import { MQuestTicket } from 'app/shared/model/m-quest-ticket.model';

describe('Component Tests', () => {
  describe('MQuestTicket Management Update Component', () => {
    let comp: MQuestTicketUpdateComponent;
    let fixture: ComponentFixture<MQuestTicketUpdateComponent>;
    let service: MQuestTicketService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestTicketUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MQuestTicketUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MQuestTicketUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MQuestTicketService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MQuestTicket(123);
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
        const entity = new MQuestTicket();
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
