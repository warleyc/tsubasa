/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTicketQuestWorldUpdateComponent } from 'app/entities/m-ticket-quest-world/m-ticket-quest-world-update.component';
import { MTicketQuestWorldService } from 'app/entities/m-ticket-quest-world/m-ticket-quest-world.service';
import { MTicketQuestWorld } from 'app/shared/model/m-ticket-quest-world.model';

describe('Component Tests', () => {
  describe('MTicketQuestWorld Management Update Component', () => {
    let comp: MTicketQuestWorldUpdateComponent;
    let fixture: ComponentFixture<MTicketQuestWorldUpdateComponent>;
    let service: MTicketQuestWorldService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTicketQuestWorldUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MTicketQuestWorldUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MTicketQuestWorldUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTicketQuestWorldService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MTicketQuestWorld(123);
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
        const entity = new MTicketQuestWorld();
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
