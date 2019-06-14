/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTrainingCardUpdateComponent } from 'app/entities/m-training-card/m-training-card-update.component';
import { MTrainingCardService } from 'app/entities/m-training-card/m-training-card.service';
import { MTrainingCard } from 'app/shared/model/m-training-card.model';

describe('Component Tests', () => {
  describe('MTrainingCard Management Update Component', () => {
    let comp: MTrainingCardUpdateComponent;
    let fixture: ComponentFixture<MTrainingCardUpdateComponent>;
    let service: MTrainingCardService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTrainingCardUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MTrainingCardUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MTrainingCardUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTrainingCardService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MTrainingCard(123);
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
        const entity = new MTrainingCard();
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
