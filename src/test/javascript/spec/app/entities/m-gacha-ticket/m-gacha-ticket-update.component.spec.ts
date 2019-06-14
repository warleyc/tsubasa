/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaTicketUpdateComponent } from 'app/entities/m-gacha-ticket/m-gacha-ticket-update.component';
import { MGachaTicketService } from 'app/entities/m-gacha-ticket/m-gacha-ticket.service';
import { MGachaTicket } from 'app/shared/model/m-gacha-ticket.model';

describe('Component Tests', () => {
  describe('MGachaTicket Management Update Component', () => {
    let comp: MGachaTicketUpdateComponent;
    let fixture: ComponentFixture<MGachaTicketUpdateComponent>;
    let service: MGachaTicketService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaTicketUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MGachaTicketUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MGachaTicketUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGachaTicketService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MGachaTicket(123);
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
        const entity = new MGachaTicket();
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
