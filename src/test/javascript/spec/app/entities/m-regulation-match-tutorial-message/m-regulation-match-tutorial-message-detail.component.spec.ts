/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MRegulationMatchTutorialMessageDetailComponent } from 'app/entities/m-regulation-match-tutorial-message/m-regulation-match-tutorial-message-detail.component';
import { MRegulationMatchTutorialMessage } from 'app/shared/model/m-regulation-match-tutorial-message.model';

describe('Component Tests', () => {
  describe('MRegulationMatchTutorialMessage Management Detail Component', () => {
    let comp: MRegulationMatchTutorialMessageDetailComponent;
    let fixture: ComponentFixture<MRegulationMatchTutorialMessageDetailComponent>;
    const route = ({ data: of({ mRegulationMatchTutorialMessage: new MRegulationMatchTutorialMessage(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MRegulationMatchTutorialMessageDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MRegulationMatchTutorialMessageDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MRegulationMatchTutorialMessageDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mRegulationMatchTutorialMessage).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
