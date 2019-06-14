/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMarathonEffectiveCardUpdateComponent } from 'app/entities/m-marathon-effective-card/m-marathon-effective-card-update.component';
import { MMarathonEffectiveCardService } from 'app/entities/m-marathon-effective-card/m-marathon-effective-card.service';
import { MMarathonEffectiveCard } from 'app/shared/model/m-marathon-effective-card.model';

describe('Component Tests', () => {
  describe('MMarathonEffectiveCard Management Update Component', () => {
    let comp: MMarathonEffectiveCardUpdateComponent;
    let fixture: ComponentFixture<MMarathonEffectiveCardUpdateComponent>;
    let service: MMarathonEffectiveCardService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMarathonEffectiveCardUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MMarathonEffectiveCardUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MMarathonEffectiveCardUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MMarathonEffectiveCardService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MMarathonEffectiveCard(123);
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
        const entity = new MMarathonEffectiveCard();
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
