/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MRegulationMatchTutorialMessageUpdateComponent } from 'app/entities/m-regulation-match-tutorial-message/m-regulation-match-tutorial-message-update.component';
import { MRegulationMatchTutorialMessageService } from 'app/entities/m-regulation-match-tutorial-message/m-regulation-match-tutorial-message.service';
import { MRegulationMatchTutorialMessage } from 'app/shared/model/m-regulation-match-tutorial-message.model';

describe('Component Tests', () => {
  describe('MRegulationMatchTutorialMessage Management Update Component', () => {
    let comp: MRegulationMatchTutorialMessageUpdateComponent;
    let fixture: ComponentFixture<MRegulationMatchTutorialMessageUpdateComponent>;
    let service: MRegulationMatchTutorialMessageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MRegulationMatchTutorialMessageUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MRegulationMatchTutorialMessageUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MRegulationMatchTutorialMessageUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MRegulationMatchTutorialMessageService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MRegulationMatchTutorialMessage(123);
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
        const entity = new MRegulationMatchTutorialMessage();
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
